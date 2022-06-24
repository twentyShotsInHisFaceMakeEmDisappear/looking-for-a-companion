package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.TruncatedNodeDto;

public interface NodeServiceInterface {

    TruncatedNodeDto getNodesByCategoryId(Long categoryId);

}
