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

package org.apache.isis.metamodel.facets.object.wizard;

import org.apache.isis.metamodel.facetapi.Facet;
import org.apache.isis.metamodel.spec.feature.ObjectAction;

/**
 * Indicates that this class is a wizard.
 */
public interface WizardFacet extends Facet {


    public void next(Object pojo);
    public String disableNext(Object pojo);

    public void previous(Object pojo);
    public String disablePrevious(Object pojo);

    public Object finish(Object pojo);
    public String disableFinish(Object pojo);

    boolean isWizardAction(ObjectAction input);
}
