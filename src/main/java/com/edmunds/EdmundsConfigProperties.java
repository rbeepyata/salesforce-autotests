package com.edmunds;

import com.epam.automation.core.ConfigProperties;

public abstract class EdmundsConfigProperties extends ConfigProperties {
	
	   //Aria
    public static final String ARIA_ENDPOINT_URI = CONFIG_DATA.get("Aria Endpoint URI");
    public static final Long ARIA_CLIENT_NO = Long.valueOf(CONFIG_DATA.get("Aria Client No"));
    public static final String ARIA_AUTH_KEY = CONFIG_DATA.get("Aria Auth Key");
    public static final Long ARIA_INTEGRATION_WAITING_TIME = Long.valueOf(CONFIG_DATA.get("Aria Integration Waiting Time"));
    
    //Workbench Developerforce
    public static final String WORKBENCH_URL = CONFIG_DATA.get("Workbench URL");
    public static final Boolean RUN_CLEAN_UP = Boolean.valueOf(CONFIG_DATA.get("Run Clean Up"));
 

}
