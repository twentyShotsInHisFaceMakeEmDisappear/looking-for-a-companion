package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.TruncatedNodeDto;
import by.grsu.lookingforacompanion.entity.Node;
import by.grsu.lookingforacompanion.exception.common.NothingToShowException;
import by.grsu.lookingforacompanion.repository.NodeRepository;
import by.grsu.lookingforacompanion.service.NodeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NodeService implements NodeServiceInterface {

    private final ModelMapper mapper;

    private final NodeRepository nodeRepository;

    @Override
    public TruncatedNodeDto getNodesByCategoryId(Long categoryOwnerId) {
        List<Node> currentNodes = nodeRepository.getNodesBySubCategoryCategoryOwnerId(categoryOwnerId);

        if (Objects.isNull(currentNodes) || currentNodes.isEmpty())
            throw new NothingToShowException("There is nothing to show.");

        return mapper.map(currentNodes, TruncatedNodeDto.class);
    }

}
