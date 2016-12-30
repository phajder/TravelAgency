package com.hajder.travelagency.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Piotr on 20.12.2016.
 * @author Piotr Hajder
 */
@ManagedBean
@SessionScoped
public class LocaleBean {
    private static Map<String, Object> locales;

    private Locale locale;

    static {
        locales = new LinkedHashMap<>();
        locales.put("Polski", new Locale("pl"));
        locales.put("English", Locale.ENGLISH);
    }

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Map<String, Object> getLocalesInMap() {
        return locales;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
