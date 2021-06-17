package com.demo.hiperQuiz.dao.impl;

import com.demo.hiperQuiz.exception.EntityAlreadyExistsException;
import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.exception.EntityUpdateException;
import com.demo.hiperQuiz.dao.UserRepository;
import com.demo.hiperQuiz.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    @Transactional
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
        em.persist(entity);
            return entity;
        }


    @Override
    public User update(User user) throws EntityNotFoundException {
        Optional<User> old = findById(user.getId());
        if(old.isEmpty()){
            throw new EntityNotFoundException(String.format("Entity with ID='%s' does not exist.",
                    user.getId()));
        }
        return em.merge(user);

    }

    @Override
    public User deleteById(Long id) throws EntityNotFoundException {
        Optional<User> user = findById(id);
        if(user.isEmpty()) {
            throw new EntityNotFoundException("User does not exist");
        }
        em.remove(user.get());
        return user.get();
    }



    @Override
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
    }

    @Override
    public void drop() {
        em.createQuery("DELETE FROM User").executeUpdate();
    }

    @Transactional
    @Override
    public int createBatch(Collection<User> entities){
        List<User> results = new ArrayList<>();
        for (User u : entities) {
            em.persist(u);
            results.add(u);
        }
        return results.size();
    }

    @Override
    public Optional<User> findByUsername(String userName){

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
        return Optional.of(query.setParameter(1, userName).getSingleResult());
    }
}
