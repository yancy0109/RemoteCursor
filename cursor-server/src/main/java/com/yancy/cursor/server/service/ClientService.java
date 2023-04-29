package com.yancy.cursor.server.service;

import com.yancy.cursor.server.client.ClientObject;

/**
 * @author yancy0109
 */
public interface ClientService {

    void addClient(ClientObject clientObject);

    void closeClient();

}
