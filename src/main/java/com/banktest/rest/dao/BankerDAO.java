package com.banktest.rest.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.banktest.banker.api.Banker;

import io.dropwizard.hibernate.AbstractDAO;
/**
 * BankerDAO is responsible for implementing the persistence functionality of the Bank
 * @author Abdullah
 *
 */
public class BankerDAO extends AbstractDAO<Banker> {
    public BankerDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Banker> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Banker create(Banker person) {
        return persist(person);
    }

    public List<Banker> findAll() {
        return list(namedQuery("com.hsbctest.banker.api.Banker.findAll"));
    }
}
