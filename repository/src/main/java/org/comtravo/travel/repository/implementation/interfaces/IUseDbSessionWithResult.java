package org.comtravo.travel.repository.implementation.interfaces;

import org.hibernate.Session;

public interface IUseDbSessionWithResult<T> {
    T Use(Session session);
}
