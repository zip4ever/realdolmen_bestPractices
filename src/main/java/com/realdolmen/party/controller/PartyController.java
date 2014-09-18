package com.realdolmen.party.controller;

import com.realdolmen.party.domain.Party;
import com.realdolmen.party.service.PartyService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

/**
 * Created by BPTAT47 on 18/09/2014.
 */
@ManagedBean
@ViewScoped
public class PartyController {

    @Inject
    private PartyService partyService;

    private List<Party> parties;

    private Party party;

    @PostConstruct
    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {

            parties = findAllParties();
            party = new Party();
        }
    }

    public List<Party> findAllParties(){
        return  partyService.getAllParties();
    }

    public String createAParty(){
        partyService.persistAParty(party);
        return "index?faces-redirect=true";
    }


    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
