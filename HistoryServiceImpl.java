package com.miniProject.miniProjectTask.service;

import com.miniProject.miniProjectTask.repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl {
    @Autowired
    private HistoryRepo historyRepo;
}
