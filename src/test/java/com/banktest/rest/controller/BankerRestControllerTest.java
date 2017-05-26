package com.banktest.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

import java.util.List;

import javax.validation.Validator;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;

import com.banktest.banker.api.Banker;
import com.banktest.banker.api.DealParameters;
import com.banktest.rest.basicauth.AppAuthenticator;
import com.banktest.rest.basicauth.AppAuthorizer;
import com.banktest.rest.basicauth.User;
import com.banktest.rest.controller.BankerRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import com.ritaja.xchangerate.util.Currency;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * Unit tests for {@link BankerRestController}.
 * TODO: Work in progress, problems found in authentication api dropwizard
 */
@RunWith(MockitoJUnitRunner.class)
public class BankerRestControllerTest {
    private static final Validator VALIDATOR = mock(Validator.class);
    @ClassRule
    public static final ResourceTestRule RESOURCES = ResourceTestRule.builder()
    		.setTestContainerFactory(new GrizzlyWebTestContainerFactory())
    		.addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new AppAuthenticator())
                    .setAuthorizer(new AppAuthorizer())
                    .setRealm("App Security")
                    .buildAuthFilter()))
    				.addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
    				.addProvider(RolesAllowedDynamicFeature.class)
    				
//    				.setTestContainerFactory(new GrizzlyWebTestContainerFactory())
//    			    .addProvider(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
//    			    		//.setAuthenticator(new Object(), new AppAuthenticator())
//    			            .setAuthorizer(new AppAuthorizer())
//    			            .setRealm("SUPER SECRET STUFF")
//    			            .setPrefix("Bearer")
//    			            .buildAuthFilter()))
//    			    .addProvider(RolesAllowedDynamicFeature.class)
//    			    .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
    			    .addResource(new BankerRestController(VALIDATOR))
    				.build();
    
    
    @Captor
    private ArgumentCaptor<Banker> personCaptor;
    private Banker person;
    
    @Before
    public void setUp() {
    	person = new Banker(1, "Loke", "Guitar", "UK",new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR) );
    }

    @After
    public void tearDown() {
        reset(VALIDATOR);
    }

    @Test
    public void createPerson() throws JsonProcessingException {
        //when(PERSON_DAO.create(any(Person.class))).thenReturn(person);
        //when(BankersDB.updateEmployee(any(Integer.class), any(Banker.class))).thenReturn(person);
        final Response response = RESOURCES.target("/bankers")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer TOKEN")
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.UNAUTHORIZED);
        //assertThat(personCaptor.getValue()).isEqualTo(person);
    }

    @Ignore
    @Test
    public void listPeople() throws Exception {
        final ImmutableList<Banker> people = ImmutableList.of(person);
        //when(PERSON_DAO.findAll()).thenReturn(people);

        final Response response = RESOURCES.target("/bankers")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer TOKEN")
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));
        

        assertThat(((Response) response).getStatusInfo()).isEqualTo(Response.Status.UNAUTHORIZED);
        
      final List<Banker> responseBankers = RESOURCES.target("/bankers")
      .request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer TOKEN").get(new GenericType<List<Banker>>() {
      });
       //verify(PERSON_DAO).findAll();
       assertThat(responseBankers).containsAll(people);
    }
}
