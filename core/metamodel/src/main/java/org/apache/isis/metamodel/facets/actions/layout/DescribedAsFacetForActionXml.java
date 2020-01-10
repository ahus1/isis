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

package org.apache.isis.metamodel.facets.actions.layout;

import org.apache.isis.applib.layout.component.ActionLayoutData;
import org.apache.isis.core.commons.internal.base._Strings;
import org.apache.isis.metamodel.facetapi.FacetHolder;
import org.apache.isis.metamodel.facets.all.describedas.DescribedAsFacet;
import org.apache.isis.metamodel.facets.all.describedas.DescribedAsFacetAbstract;

public class DescribedAsFacetForActionXml extends DescribedAsFacetAbstract {

    public static DescribedAsFacet create(ActionLayoutData actionLayout, FacetHolder holder) {
        if(actionLayout == null) {
            return null;
        }
        final String describedAs = _Strings.emptyToNull(actionLayout.getDescribedAs());
        return describedAs != null ? new DescribedAsFacetForActionXml(describedAs, holder) : null;
    }

    private DescribedAsFacetForActionXml(String value, FacetHolder holder) {
        super(value, holder);
    }

}
