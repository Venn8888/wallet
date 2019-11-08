package com.venn.eventbus.listen;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.Subscribe;
import com.venn.eventbus.event.MsgEvent;
import org.springframework.stereotype.Component;

/**
 * @author dwf
 * @date 2019/9/11 20:05
 */

@Component
public class MsgEventListener implements EventListener {

    @Subscribe
    public String sendMsg(MsgEvent event) {
        String jsonString = JSON.toJSONString(event);
        System.out.println("==================>" + jsonString);
        return jsonString;
    }
}
