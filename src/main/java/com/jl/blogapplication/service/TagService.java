package com.jl.blogapplication.service;


import com.jl.blogapplication.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagByName(String tagName);

    Tag getTag(Long tagId);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTags(String ids);

    Tag updateType(Long id, Tag type);

    void deleteTag(Long id);

}
