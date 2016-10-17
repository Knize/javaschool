package ru.hyperloop.ticketreport.controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import ru.knize.hyperloop.ticketsReport.DTO.TicketsInfo;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

/**
 * Created by knize on 17.10.16.
 */
@Named("controller")
@RequestScoped
public class Controller {
    public TicketsInfo getTickets() {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/api/tickets/list");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return response.getEntity(TicketsInfo.class);
    }
}
