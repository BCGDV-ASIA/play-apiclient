/*
 * ApiAwareControllerModules
 */
package com.bcgdv.play.modules;

import com.bcgdv.play.services.Api;
import com.bcgdv.play.services.ApiFacade;
import com.google.inject.AbstractModule;
import play.Configuration;
import play.Environment;

/**
 * Module to bind api client classes at runtime
 */
public class ApiAwareControllerModule extends AbstractModule {

    protected Environment environment;
    protected Configuration configuration;

    /**
     * Api module reads from env and configuration
     *
     * @param env  the play environment
     * @param conf the play configuration
     */
    public ApiAwareControllerModule(Environment env, Configuration conf) {
        this.environment = env;
        this.configuration = conf;
    }

    /**
     * Guice Bindings for Api
     */
    @Override
    public void configure() {
        bind(Api.class).to(ApiFacade.class);
    }
}
