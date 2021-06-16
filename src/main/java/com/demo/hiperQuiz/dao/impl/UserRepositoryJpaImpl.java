package com.demo.hiperQuiz.dao.impl;

import com.demo.hiperQuiz.exception.EntityAlreadyExistsException;
import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.exception.EntityUpdateException;
import com.demo.hiperQuiz.dao.UserRepository;
import com.demo.hiperQuiz.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryJpaImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;




//    public void clean() {
//        em.close();
//    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User as u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    @Transactional
    public User create(User entity) {
            entity.setId(null);
            try {
                em.persist(entity);
            }
            catch (RollbackException ex){
                throw  new EntityUpdateException("There was a problem creating the user");
            }
            return entity;
        }


    @Override
    public User update(User user) throws EntityNotFoundException {
        Optional<User> old = findById(user.getId());
        if(old.isEmpty()){
            throw new EntityNotFoundException(String.format("Entity with ID='%s' does not exist.",
                    user.getId()));
        }

        try {
            return em.merge(user);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new EntityUpdateException("Error updating entity:" + user, e);
        }
    }

    @Override
    public User deleteById(Long id) throws EntityNotFoundException {
        Optional<User> userToDelete = findById(id);
        userToDelete.ifPresent(user -> em.remove(user));
        return userToDelete.get();

    }

    @Override
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
    }

    @Override
    public void drop() {
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        em.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
    }

    @Override
    public int createBatch(Collection<User> entities) throws EntityAlreadyExistsException, EntityAlreadyExistsException {
        return 0;
    }

    @Override
    public Optional<User> findByUsername(String userName) throws EntityNotFoundException {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
        User user = query.setParameter(1, userName).getSingleResult();
        return Optional.of(user);
    }
}
