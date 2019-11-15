package com.sobri.app.model.service;

import com.google.inject.Inject;
import com.sobri.lib.AppService;
import com.sobri.app.model.repository.IndexRepository;

public class IndexService extends AppService {
    private IndexRepository indexRepository;

    @Inject
    public IndexService(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
    }

    public String Index() {
        return this.indexRepository.Index();
    }
}
