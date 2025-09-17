package com.debugdrawer.sampleapp.network;

/**
 * API service interface for sample requests.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\b"}, d2 = {"Lcom/debugdrawer/sampleapp/network/ApiService;", "", "getPosts", "", "Lcom/debugdrawer/sampleapp/network/Post;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsers", "Lcom/debugdrawer/sampleapp/network/User;", "sampleapp_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "users")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUsers(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.debugdrawer.sampleapp.network.User>> $completion);
    
    @retrofit2.http.GET(value = "posts")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPosts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.debugdrawer.sampleapp.network.Post>> $completion);
}