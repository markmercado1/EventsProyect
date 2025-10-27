package com.emm.authservice.enums;

public enum Role {
    ROLE_USER,       // Usuario normal - ve eventos
    ROLE_ORGANIZER,  // Crea y gestiona eventos (antes MODERATOR)
    ROLE_ADMIN       // Control total del sistema
}