/* 
 * Copyright (c) 2011-2012 VMware, Inc.
 *  
 * This file is part of the vCO PowerShell Plug-in.
 *  
 * The vCO PowerShell Plug-in is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation version 3 and no later version.
 *  
 * The vCO PowerShell Plug-in is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License version 3
 * for more details.
 *  
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.vmware.o11n.plugin.powershell.model.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmware.o11n.plugin.powershell.config.PowerShellHostConfig;
import com.vmware.o11n.plugin.powershell.model.PowerShellUserToken;
import com.vmware.o11n.plugin.powershell.model.RemoteConnector;
import com.vmware.o11n.plugin.powershell.model.Session;
import com.vmware.o11n.plugin.powershell.remote.impl.BasePowerShellTerminal;
import com.vmware.o11n.plugin.powershell.remote.impl.PowerShellTerminal;

public class SshRemoteConnector implements RemoteConnector {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(SshRemoteConnector.class);

    private PowerShellHostConfig psHost;

    public SshRemoteConnector(PowerShellHostConfig psHost) {
        this.psHost = psHost;
    }	

	@Override
	public Session openSession(PowerShellUserToken token) {
	    BasePowerShellTerminal psTerminal = new PowerShellTerminal(psHost.getConnectionURL(), token.getUserName()); 
	    Session session = new SshSession(psHost.getConnectionURL(), token.getUserName(), new String( token.getPassword()), psTerminal);
        return session;
	}

    @Override
    public void closeSession(Session session) {
        if (session != null) {
            session.disconnect();
        }
    }
}
