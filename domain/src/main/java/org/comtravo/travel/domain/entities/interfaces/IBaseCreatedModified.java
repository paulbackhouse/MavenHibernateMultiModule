package org.comtravo.travel.domain.entities.interfaces;

import java.sql.Timestamp;

public interface IBaseCreatedModified {

    public Timestamp getCreated();

    public void setCreated(Timestamp created);

    public Timestamp getModified();

    public void setModified(Timestamp modified);
}