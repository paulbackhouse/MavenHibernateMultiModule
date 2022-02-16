package org.comtravo.travel.repository.implementation.interfaces;

import org.hibernate.Session;

public interface IUseDbSession {
    void Use(Session session);
}
