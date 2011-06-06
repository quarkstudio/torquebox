/*
 * Copyright 2008-2011 Red Hat, Inc, and individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.torquebox.services.as;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.ADD;

import org.jboss.as.controller.Extension;
import org.jboss.as.controller.ExtensionContext;
import org.jboss.as.controller.SubsystemRegistration;
import org.jboss.as.controller.parsing.ExtensionParsingContext;
import org.jboss.as.controller.registry.ModelNodeRegistration;
import org.jboss.logging.Logger;
import org.torquebox.core.as.AbstractBootstrappableExtension;

public class ServicesExtension extends AbstractBootstrappableExtension {

    @Override
    public void initialize(ExtensionContext context) {
        bootstrap();
        log.info( "Initializing TorqueBox Services Subsystem" );
        final SubsystemRegistration registration = context.registerSubsystem( SUBSYSTEM_NAME );
        final ModelNodeRegistration subsystem = registration.registerSubsystemModel( ServicesSubsystemProviders.SUBSYSTEM );

        subsystem.registerOperationHandler( ADD,
                ServicesSubsystemAdd.ADD_INSTANCE,
                ServicesSubsystemProviders.SUBSYSTEM_ADD,
                false );
        
        registration.registerXMLElementWriter(ServicesSubsystemParser.getInstance());
    }

    @Override
    public void initializeParsers(ExtensionParsingContext context) {
        context.setSubsystemXmlMapping(Namespace.CURRENT.getUriString(), ServicesSubsystemParser.getInstance());
    }
    
    public static final String SUBSYSTEM_NAME = "torquebox-services";
    static final Logger log = Logger.getLogger( "org.torquebox.services.as" );

}