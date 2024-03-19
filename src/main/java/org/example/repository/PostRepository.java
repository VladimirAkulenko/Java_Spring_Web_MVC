package org.example.repository;

import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> postMap;
    private final AtomicLong postId;

    public PostRepository() {
        postMap = new ConcurrentHashMap<>();
        postId = new AtomicLong();
    }

    public List<Post> all() {
        return new ArrayList<>(postMap.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = postId.incrementAndGet();
            post.setId(id);
            postMap.put(id, post);
        } else if (post.getId() != 0) {
            long currentId = post.getId();
            postMap.put(currentId, post);
        }
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
