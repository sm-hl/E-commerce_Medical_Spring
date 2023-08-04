package com.miniprojet.miniprojet.Service;

import java.util.Locale;

import com.miniprojet.miniprojet.Configuration.CustomProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {
    @Autowired MessageSource messageSource;
    @Autowired CustomProperties customProperties;
    
    /**
     * Recuperer le message dans le fichier messages_xx.properties
     * @param code : un code de message comme <code>account_not_found</code>
     * @return Un message de type <code>String</code> récupéré par le <code>code</code>
     */
    public String getMessage(String code)
    {
        return messageSource.getMessage(code, null, new Locale(customProperties.getLanguage()));
    }
}
