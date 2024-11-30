package com.easyapi.core.doc;

import com.easyapi.core.parser.ControllerNode;

import java.io.IOException;


public interface IControllerDocBuilder {

    String buildDoc(ControllerNode controllerNode) throws IOException;

}
