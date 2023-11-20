package com.tang.core.modules.api.chat.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * choice和object同时存在是以object为准
 *
 * @author <a href="https://www.unfbx.com">unfbx</a>
 * @since 1.1.2
 * 2023-11-09
 */
@Data
public class ToolChoice implements Serializable {

    @Getter
    @AllArgsConstructor
    public enum Choice {
        NONE("none"),
        AUTO("auto"),
        ;
        private final String name;
    }
}
