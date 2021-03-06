/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.webapp.webappctx;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Configuration @Log4j2
public class IsisWebAppContextInitializer implements ServletContextInitializer {

    // holder of ServletContext with one-shot access 
    public static class ServletContextResource {
        private ServletContext servletContext;
        public ServletContext getServletContextOneShot() {
            try {
                return servletContext;    
            } finally {
                servletContext = null;
            }
        }
    }
    
    @Bean @Singleton
    public ServletContextResource getServletContextResource() {
        return servletContextResource;
    }
    
    private final ServletContextResource servletContextResource = new ServletContextResource(); 
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Memoizing the ServletContext.");
        servletContextResource.servletContext = servletContext;
    }
}
