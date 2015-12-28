package com.iacrs.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mock")
public class MockAction extends BaseAction
{
    @RequestMapping("/forward_mock.do")
    public String forwardMock()
    {
        return "mock/mock";
    }
}
