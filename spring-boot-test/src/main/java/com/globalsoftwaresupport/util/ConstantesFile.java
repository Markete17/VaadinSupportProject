package com.globalsoftwaresupport.util;

/**
 *
 * @author 06551256M
 */
public class ConstantesFile {

    public static final String TOMCATPATH = System.getProperty("catalina.base");

    public static final String NOMBREDIRECTORIO = "datos";

    public static final String EXTENSIONJSON = ".json";

    public static final String FILESEPARATOR = System.getProperty("file.separator");

    public static final String URLDIRECTORIO = System.getProperty("file.separator") + NOMBREDIRECTORIO + System.getProperty("file.separator");

    public static final String PATHABSDIRECTORIO = TOMCATPATH + System.getProperty("file.separator") + NOMBREDIRECTORIO + System.getProperty("file.separator");

}
