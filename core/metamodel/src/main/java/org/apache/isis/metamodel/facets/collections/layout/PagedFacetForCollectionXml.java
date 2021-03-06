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

package org.apache.isis.metamodel.facets.collections.layout;

import org.apache.isis.applib.layout.component.CollectionLayoutData;
import org.apache.isis.metamodel.facetapi.FacetHolder;
import org.apache.isis.metamodel.facets.object.paged.PagedFacet;
import org.apache.isis.metamodel.facets.object.paged.PagedFacetAbstract;

public class PagedFacetForCollectionXml extends PagedFacetAbstract {

    public static PagedFacet create(CollectionLayoutData collectionLayout, FacetHolder holder) {
        if(collectionLayout == null) {
            return null;
        }
        final Integer paged = collectionLayout.getPaged();
        return paged != null && paged != -1 ? new PagedFacetForCollectionXml(paged, holder) : null;
    }

    private PagedFacetForCollectionXml(int paged, FacetHolder holder) {
        super(paged, holder);
    }

}
