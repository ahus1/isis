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

package org.apache.isis.security.api.authentication.fixtures;

import java.util.Collection;
import java.util.Collections;

import org.apache.isis.core.commons.internal.collections._Lists;
import org.apache.isis.security.api.authentication.AuthenticationRequestAbstract;
import org.apache.isis.security.api.authentication.manager.AuthenticationManager;

/**
 * For testing purposes, request corresponding to a {@link LogonFixture}.
 *
 * <p>
 * Understood directly by {@link AuthenticationManager}.
 */
public class AuthenticationRequestLogonFixture extends AuthenticationRequestAbstract {

    public static AuthenticationRequestLogonFixture of(final String name, final String ... roles) {
        return new AuthenticationRequestLogonFixture(name, 
                roles == null 
                ? Collections.emptyList()
                        : _Lists.of(roles));
    }

    public AuthenticationRequestLogonFixture(final String name, final Collection<String> roles) {
        super(name);
        addRoles(roles);
    }

}
