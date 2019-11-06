package com.venn.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author dwf
 * @date 2019/9/27 22:15
 */

@Data
public class RealDataDTO implements Serializable {

  private static final long serialVersionUID = -8036725687536277312L;

  private String name;

  private Long age;

  private Double height;
}
