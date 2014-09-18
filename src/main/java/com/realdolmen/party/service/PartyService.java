package com.realdolmen.party.service;

import com.realdolmen.party.domain.Party;
import com.realdolmen.party.domain.Person;
import com.realdolmen.party.repositorie.PartyRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by BPTAT47 on 18/09/2014.
 */
@Stateless
public class PartyService {
    @Inject
    private PartyRepository partyRepository;

    public List<Party> getAllParties(){
        return partyRepository.findAll();
    }

    public void persistAParty(Party party){
        partyRepository.persist(party);
    }
}
