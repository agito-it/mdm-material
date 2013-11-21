package org.agito.demo.mdm.material;


import de.agito.cps.core.bpmo.api.enums.ILanguage;
import java.util.Locale;


/**
 * Languages for MaterialBPMO.
 *
 * @author andreas.weise
 */
public enum MaterialBPMOLanguage implements ILanguage {

	en("en", new Locale("en"), true);

	private MaterialBPMOLanguage(String code, Locale locale, boolean defaultIndicator) { this.code = code; this.locale = locale; this.defaultIndicator = defaultIndicator; }
	private String code;
	private boolean defaultIndicator;
	private Locale locale;
	public String getCode() { return code; }
	public boolean isDefault() { return defaultIndicator; }
	public Locale getLocale() { return locale; }

}

