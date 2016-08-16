
package com.gabriel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gabriel
 */
@Controller
public class ApplicationController {
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
