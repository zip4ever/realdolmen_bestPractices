package com.realdolmen.party.repositorie;



import com.realdolmen.party.domain.Party;
import com.realdolmen.party.domain.Person;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by BPTAT47 on 16/09/2014.
 */
@Singleton
@Startup
public class TestData {

   @PersistenceContext(name = "")
   private EntityManager entityManager;


    @PostConstruct
    public void initializeData(){
        Person person = new Person("Benjamin","Pieteraerents","admin","admin",new Date(),'M');
        Party party = new Party("Test",12.00);
        Party party1 = new Party("Test",12.00);

        entityManager.persist(person);
        entityManager.persist(party);
        entityManager.persist(party1);
        entityManager.flush();
    }

}
