//package com.demo.hiperQuiz;
//
//import com.demo.hiperQuiz.commands.*;
//import com.demo.hiperQuiz.dao.UserRepository;
//import com.demo.hiperQuiz.dao.impl.*;
//import com.demo.hiperQuiz.exception.EntityNotFoundException;
//import com.demo.hiperQuiz.model.User;
//import com.demo.hiperQuiz.service.QuizService;
//import com.demo.hiperQuiz.service.impl.UserServiceImpl;
//import com.demo.hiperQuiz.util.Alignment;
//import com.demo.hiperQuiz.util.PrintUtil;
//import com.demo.hiperQuiz.dao.PlayerRepository;
//import com.demo.hiperQuiz.dao.QuizRepository;
//import com.demo.hiperQuiz.dao.QuizResultRepository;
//import com.demo.hiperQuiz.model.Player;
//import com.demo.hiperQuiz.model.Quiz;
//import com.demo.hiperQuiz.service.UserService;
//import com.demo.hiperQuiz.service.impl.QuizServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.*;
//import java.util.stream.Collectors;
//
////@Slf4j
////@Component
//public class Engine {//implements CommandLineRunner {
//
//    private static final Map<String, CommandMenu> commands = new HashMap<>();
//    private final Scanner scanner;
//    private QuizService quizService;
//    private final UserRepository userRepository;
//    private final QuizResultRepository quizResultRepository;
//    private final PlayerRepository playerRepository;
//    private User user;
//   // private final UserService userService;
//    private Player player = null;
//    private final QuizRepository quizRepository;
//    private final PrintingCommand printingCommand;
//    private final ReadingCommand readingCommand;
//    @Autowired
//    private final UserRepositoryJpaImpl userRepositoryJpa;
//
//
//
//    public Engine(UserRepositoryJpaImpl userRepositoryJpa) {
//        this.userRepositoryJpa = userRepositoryJpa;
//        this.printingCommand = new PrintingCommand();
//        scanner = new Scanner(System.in);
//        this.readingCommand = new ReadingCommand(scanner);
//        this.userRepository = new UserRepositoryImpl(new LongKeyGenerator());
//        this.quizResultRepository = new QuizResultRepositoryImpl(new LongKeyGenerator());
//        this.playerRepository = new PlayerRepositoryImpl(new LongKeyGenerator());
//        this.quizRepository = new QuizRepositoryImpl(new LongKeyGenerator());
//       // this.userService = new UserServiceImpl(userRepository, printingCommand, readingCommand);
//        this.quizService = new QuizServiceImpl(quizRepository, printingCommand, readingCommand, quizResultRepository);
//        user = new User();
//
//    }
//
//
////
// //  @Override
//   public void run(String... args) throws Exception {
//
//
//
////        String propsParh = "C:\\Users\\elena.tasheva\\IdeaProjects\\hiperQuiz-maven\\src\\main\\resources\\db.properties";
////        Properties props = new Properties();
////        props.load(new FileInputStream(propsParh));
////        List<User> users = new ArrayList<>();
////
////
////         2. Load DB driver
////        try {
////            Class.forName(props.getProperty("driver"));
////            System.out.println("DB driver loaded");
////        } catch (ClassNotFoundException e) {
////            System.out.println("Error: DB driver not found");
////            e.printStackTrace();
////            return;
////        }
////
////         3. Connect to DB
////        try (Connection connection = DriverManager.getConnection(props.getProperty("url"), props)) {
////            System.out.printf("Successfully connected to: %s%n", props.getProperty("url"));
////            // 4. Create and execute PreparedStatement
////            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
////            ResultSet rs = stmt.executeQuery();
////
////            while(rs.next()){
////                StringBuilder sb = new StringBuilder();
////                sb.append(rs.getInt("id")).append(", ").append(rs.getString("username"))
////                        .append(",").append(rs.getString("email")).append(", ")
////                        .append(rs.getBoolean("status"));
////                // TODO: 6/9/2021 fix data
////                Date created = rs.getDate("created");
////                sb.append(", ").append(created);
////                Gender gender1 = Gender.valueOf(rs.getString("gender"));
////                sb.append(", ").append(gender1);
////                System.out.println(sb);
////                User user = new User();
////                user.setId(rs.getLong("id"));
////                user.setUsername(rs.getString("username"));
////                user.setPassword(rs.getString("password"));
////                user.setEmail(rs.getString("email"));
////                user.setGender(gender1);
////               // user.setCreated(created);
////                users.add(user);
////
////
////        }
////        catch (SQLException throwable) {
////            throwable.printStackTrace();
////        }
////
////        for (User user1 : users) {
////            System.out.println(user1);
////        }
//
//
//      //  loadData();
//        fillInCommands();
//        printMenu();
//
//        String command = scanner.nextLine();
//
//        while (true) {
//            CommandMenu commandMenu = commands.get(command);
//            while (commandMenu == null) {
//                System.out.println("Invalid command. Please try again");
//                command = scanner.nextLine();
//                commandMenu = commands.get(command);
//            }
//                switch (commandMenu){
//                    case EXIT:
//                    try {
//                        SaveEntitiesCommand saveCommand = new SaveEntitiesCommand(new FileOutputStream("quizzes.db"),
//                                playerRepository, userRepository, quizService.getQuizRepository(), quizResultRepository);
//                        System.out.println(saveCommand.execute());
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("Thank you! BYE :))");
//                    System.exit(0);
//                    break;
//
//                    case CREATE_QUIZ:
//                        user = userService.logIn(user);
//                        settingPlayer();
//                        Quiz quiz = quizService.createQuiz(user);
//                        user.getQuizzes().add(quiz);
//                        break;
//
//                    case CREATE_USER:
//                    user = this.userService.createUser();
//                    player = new Player(user.getUsername());
//                    playerRepository.create(player);
//                    System.out.printf("User repository size is: %d%n", userRepositoryJpa.count());
//                    userRepositoryJpa.create(user);
//                    settingPlayer();
//                    // testing UserJPARepo
//                        System.out.printf("User repository size is: %d%n", userRepositoryJpa.count());
//                    // change the menu;
//                    break;
//
//                    case LOAD_QUIZEZ:
//                        printQuizez();
//
//                    case PLAY_QUIZ:
//                    printQuizez();
//                    // todo optimize method
//                    userService.logIn(user);
//                    settingPlayer();
//                    player = quizService.play(player);
//
//
//                    break;
//
//                    case LOAD_USERS:
//                    printUsers();
//
//                    break;
//                    case EDIT_USER:
//                   // userLogin();
//                    System.out.println("Enter a new password");
//                    String password = scanner.nextLine();
////                    try {
////                        this.userService.updatePassword(user, password);
////                        System.out.println("Your password has been change successfully");
////                    } catch (EntityNotFoundException e) {
////                        System.out.println(e.getMessage());
////                    }
//                        //                    try {
//                        try {
//                            user.setPassword(password);
//                            User editedUser =  this.userRepositoryJpa.update(user);
//                            System.out.println("Your password has been changed successfully");
//                            System.out.printf("Return managed instance: %s%n", editedUser == user);
//
//                        } catch (EntityNotFoundException e) {
//                            //log.error(String.format("Error updating user"), e);
//                        }
//                        // testing delete
//                        System.out.println("Press 'Y' if you want to delete your profile");
//                        String delete = scanner.nextLine();
//                        if(delete.equals("Y")){
//                            try {
//                                long id = userRepositoryJpa.findByUsername(user.getUsername()).get().getId();
//                                System.out.println(id);
//                                userRepositoryJpa.deleteById(id);
//                            } catch (EntityNotFoundException e) {
//                             //   log.error(String.format("Error deleting user"), e);
//                            }
//                        }
//                        userRepositoryJpa.drop();
//                    break;
//            }
//            printMenu();
//            command = scanner.nextLine();
//        }
//    }
//
//
//
//    private void loadData()  {
//
//        quizRepository.drop();
//        quizResultRepository.drop();
//        userRepository.drop();
//        playerRepository.drop();
//
//        LoadEntitiesCommand loadCommand = null;
//        try {
//           loadCommand = new LoadEntitiesCommand(new FileInputStream("quizzes.db"),
//                    playerRepository, userRepository, quizRepository, quizResultRepository);
//        }
//        catch (FileNotFoundException ex){
//            ex.printStackTrace();
//        }
//        System.out.println(loadCommand.execute());
//
//    }
//
//    private void printUsers() {
//
//        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, Alignment.CENTER),
//                new PrintUtil.ColumnDescriptor("username", "Username", 10, Alignment.LEFT),
//                new PrintUtil.ColumnDescriptor("overallScore", "overallScore", 12, Alignment.LEFT),
//                new PrintUtil.ColumnDescriptor("rank", "rank", 20, Alignment.LEFT)
//
//        ));
//
//
//        String userReport = PrintUtil.formatTable(userColumns, playerRepository.findAll().stream().sorted(Comparator.comparing(Player::getOverallScore).reversed()).collect(Collectors.toList()), "STATISTIC");
//        System.out.println(userReport);
//    }
//
//
//
//
//    private void settingPlayer() {
//        Optional<Player> player = this.playerRepository.findAll().stream()
//                .filter(player1 -> player1.getUsername().equals(user.getUsername())).findFirst();
//        // user is found ==> player is found as well as we commit them together in the repos;
//        this.player = player.get();
//    }
//
//
//
//
//    private void printMenu() {
//        System.out.println("---MENU---\n -Please enter a number-\n"+
//                "~0~ Exit\n" +
//                "~1~ Create Quiz\n" +
//                "~2~ Create User\n"+
//                "~3~ Pick a Quiz\n"+ // printing quizzez
//                "~4~ Print Users\n"+
//                "~5~ Edit Profile");
//    }
//
//
//    private void printQuizez() {
//
//        List<PrintUtil.ColumnDescriptor> metadataColumns = getMetaData();
//;
//        List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, Alignment.CENTER),
//                new PrintUtil.ColumnDescriptor("title", "Title", 15, Alignment.LEFT),
//                new PrintUtil.ColumnDescriptor("author", "Author", 12, Alignment.LEFT),
//                new PrintUtil.ColumnDescriptor("description", "Description", 20, Alignment.LEFT),
//                new PrintUtil.ColumnDescriptor("expectedDuration", "Duration", 8, Alignment.RIGHT, 2),
//                        //   new PrintUtil.ColumnDescriptor("text", "Question", 8, RIGHT, 2),
//                new PrintUtil.ColumnDescriptor("URL", "Picture URL", 11, Alignment.CENTER)
//        ));
//
//                quizColumns.addAll(metadataColumns);
//
//        String quizReport = PrintUtil.formatTable(quizColumns, quizService.findAll(), "Quiz List:");
//        System.out.println(quizReport);
//
//    }
//
//    private List<PrintUtil.ColumnDescriptor> getMetaData() {
//        return  List.of(
//                new PrintUtil.ColumnDescriptor("created", "Created", 19, Alignment.CENTER),
//                new PrintUtil.ColumnDescriptor("updated", "Updated", 19, Alignment.CENTER)
//        );
//    }
//
//
//    private void fillInCommands() {
//        commands.put("0", CommandMenu.EXIT);
//        commands.put("1", CommandMenu.CREATE_QUIZ);
//        commands.put("2", CommandMenu.CREATE_USER);
//        commands.put("3", CommandMenu.LOAD_QUIZEZ);
//        commands.put("4", CommandMenu.LOAD_USERS);
//        commands.put("5", CommandMenu.EDIT_USER);
//    }
//
//
//
//}
//
//
//
//
//
//
