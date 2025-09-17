package com.debugdrawer.sampleapp.network;

/**
 * Sample network client for demonstrating network logging.
 */
@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/debugdrawer/sampleapp/network/SampleNetworkClient;", "", "networkInterceptor", "Lcom/debugdrawer/utils/NetworkInterceptor;", "(Lcom/debugdrawer/utils/NetworkInterceptor;)V", "apiService", "Lcom/debugdrawer/sampleapp/network/ApiService;", "getPosts", "", "Lcom/debugdrawer/sampleapp/network/Post;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsers", "Lcom/debugdrawer/sampleapp/network/User;", "sampleapp_debug"})
public final class SampleNetworkClient {
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.utils.NetworkInterceptor networkInterceptor = null;
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.sampleapp.network.ApiService apiService = null;
    
    @javax.inject.Inject
    public SampleNetworkClient(@org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.NetworkInterceptor networkInterceptor) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUsers(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.debugdrawer.sampleapp.network.User>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getPosts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.debugdrawer.sampleapp.network.Post>> $completion) {
        return null;
    }
}