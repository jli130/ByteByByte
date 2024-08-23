package com.jl.blogapplication.service;

import com.jl.blogapplication.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment();

}
