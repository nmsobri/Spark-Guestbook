package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.entity.IndexEntity;

public class IndexRepository {
    private IndexEntity indexEntity;

    @Inject
    public IndexRepository(IndexEntity indexEntity) {
        this.indexEntity = indexEntity;
    }

    public String Index() {
        return this.indexEntity.Index();
    }
}
