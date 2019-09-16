package io.vertx.ext.web.sstore.redis;


import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.sstore.SessionStore;

/**
 * A SessionStore that uses Redis DB as storage for session data. All data is stored 'as is' in the Redis DB.
 *
 * @author <a href="mailto:abilio.esteves@ibm.com">Abilio Esteves</a>
 */
@VertxGen
public interface RedisSessionStore extends SessionStore {



}
