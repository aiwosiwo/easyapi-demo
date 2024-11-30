package com.easyapi.core;

import com.easyapi.core.parser.ControllerNode;

import java.util.List;

public interface IPluginSupport {

    void execute(List<ControllerNode> controllerNodeList);
}
