package com.springcourse.dao;

import com.springcourse.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

       /* // 1 Query
        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();

        // N Query
        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }*/

        Set<Person> people = new HashSet<>(session.createQuery("select p from Person p left join fetch p.items")
                .getResultList());

        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }
}
