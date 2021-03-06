package com.venn.eventbus.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dwf
 * @date 2019/9/11 19:53
 */

@Data
public class MsgEvent implements Serializable {

    private static final long serialVersionUID = 6846735158414156885L;

    private String name;

    private String msg;

}
