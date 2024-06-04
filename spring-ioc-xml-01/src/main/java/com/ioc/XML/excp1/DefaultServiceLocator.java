package com.ioc.XML.excp1;

//实例工厂
public class DefaultServiceLocator {
    private static ClientServiceImpl clientService= new ClientServiceImpl();

    public ClientServiceImpl createClientServiceInstance() {
        return clientService;
    }
}
