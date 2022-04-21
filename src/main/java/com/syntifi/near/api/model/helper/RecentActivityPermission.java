package com.syntifi.near.api.model.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.accesskey.permission.Permission;
import com.syntifi.near.api.model.accesskey.permission.PermissionTypeData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RecentActivityPermission
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentActivityPermission {
    public enum PermissionKind {
        FULL_ACCESS,
    }

    @JsonProperty("permission_kind")
    private PermissionKind permissionKind;
}
