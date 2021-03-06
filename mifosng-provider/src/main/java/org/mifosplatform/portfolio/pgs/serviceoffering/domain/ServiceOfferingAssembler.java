/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.pgs.serviceoffering.domain;

import java.util.Locale;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.pgs.serviceaccount.domain.ServiceAccount;
import org.mifosplatform.portfolio.pgs.serviceaccount.domain.ServiceAccountRepository;
import org.mifosplatform.portfolio.pgs.serviceoffering.api.ServiceOfferingApiConstants;
import org.mifosplatform.portfolio.pgs.serviceoffering.exception.ServiceOfferingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;

@Component
public class ServiceOfferingAssembler {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private final FromJsonHelper fromApiJsonHelper;
	private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    public ServiceOfferingAssembler(final ServiceOfferingRepository serviceOfferingRepository,
    		final ServiceAccountRepository serviceAccountRepository,
            final FromJsonHelper fromApiJsonHelper) {
        this.serviceOfferingRepository = serviceOfferingRepository;
        this.serviceAccountRepository = serviceAccountRepository;
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public ServiceOffering assembleFromJson(final JsonCommand command) {

        final JsonElement element = command.parsedJson();
        String name = null;
        String description = null;
        Integer feeStructure = null;
        Long serviceAccountId = null;
        
        if(command.getServiceAccountId() != null){
        	serviceAccountId = command.getServiceAccountId();
        }
        
        final ServiceAccount serviceAccount = this.serviceAccountRepository.findOne(serviceAccountId);
        
        if (this.fromApiJsonHelper.parameterExists(ServiceOfferingApiConstants.nameParamName, element)) {
            name = this.fromApiJsonHelper.extractStringNamed(ServiceOfferingApiConstants.nameParamName, element);
        }

        if (this.fromApiJsonHelper.parameterExists(ServiceOfferingApiConstants.descriptionParamName, element)) {
            description = this.fromApiJsonHelper.extractStringNamed(ServiceOfferingApiConstants.descriptionParamName, element);
            
        }
        
        if (this.fromApiJsonHelper.parameterExists(ServiceOfferingApiConstants.feeStructureParamName, element)) {
            feeStructure = this.fromApiJsonHelper.extractIntegerNamed(ServiceOfferingApiConstants.feeStructureParamName, element, Locale.getDefault());
            
        }

        return ServiceOffering.ServiceOffering(serviceAccount, name, description, feeStructure);
    }

    public ServiceOffering assembleFromResourceId(final Long resourceId) {
        final ServiceOffering serviceOffering = this.serviceOfferingRepository.findOne(resourceId);
        if (serviceOffering == null) { throw new ServiceOfferingNotFoundException(resourceId); }
        return serviceOffering;
    }
}