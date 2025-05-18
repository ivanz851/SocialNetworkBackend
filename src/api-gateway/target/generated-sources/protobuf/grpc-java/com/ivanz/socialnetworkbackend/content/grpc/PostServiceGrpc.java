package com.ivanz.socialnetworkbackend.content.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: post.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PostServiceGrpc {

  private PostServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "post.PostService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getCreatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePost",
      requestType = com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest.class,
      responseType = com.ivanz.socialnetworkbackend.content.grpc.PostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getCreatePostMethod() {
    io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getCreatePostMethod;
    if ((getCreatePostMethod = PostServiceGrpc.getCreatePostMethod) == null) {
      synchronized (PostServiceGrpc.class) {
        if ((getCreatePostMethod = PostServiceGrpc.getCreatePostMethod) == null) {
          PostServiceGrpc.getCreatePostMethod = getCreatePostMethod =
              io.grpc.MethodDescriptor.<com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.PostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PostServiceMethodDescriptorSupplier("CreatePost"))
              .build();
        }
      }
    }
    return getCreatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getGetPostByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPostById",
      requestType = com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest.class,
      responseType = com.ivanz.socialnetworkbackend.content.grpc.PostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getGetPostByIdMethod() {
    io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getGetPostByIdMethod;
    if ((getGetPostByIdMethod = PostServiceGrpc.getGetPostByIdMethod) == null) {
      synchronized (PostServiceGrpc.class) {
        if ((getGetPostByIdMethod = PostServiceGrpc.getGetPostByIdMethod) == null) {
          PostServiceGrpc.getGetPostByIdMethod = getGetPostByIdMethod =
              io.grpc.MethodDescriptor.<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPostById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.PostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PostServiceMethodDescriptorSupplier("GetPostById"))
              .build();
        }
      }
    }
    return getGetPostByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
      com.google.protobuf.Empty> getDeletePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeletePost",
      requestType = com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
      com.google.protobuf.Empty> getDeletePostMethod() {
    io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest, com.google.protobuf.Empty> getDeletePostMethod;
    if ((getDeletePostMethod = PostServiceGrpc.getDeletePostMethod) == null) {
      synchronized (PostServiceGrpc.class) {
        if ((getDeletePostMethod = PostServiceGrpc.getDeletePostMethod) == null) {
          PostServiceGrpc.getDeletePostMethod = getDeletePostMethod =
              io.grpc.MethodDescriptor.<com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeletePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PostServiceMethodDescriptorSupplier("DeletePost"))
              .build();
        }
      }
    }
    return getDeletePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getUpdatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePost",
      requestType = com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest.class,
      responseType = com.ivanz.socialnetworkbackend.content.grpc.PostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getUpdatePostMethod() {
    io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getUpdatePostMethod;
    if ((getUpdatePostMethod = PostServiceGrpc.getUpdatePostMethod) == null) {
      synchronized (PostServiceGrpc.class) {
        if ((getUpdatePostMethod = PostServiceGrpc.getUpdatePostMethod) == null) {
          PostServiceGrpc.getUpdatePostMethod = getUpdatePostMethod =
              io.grpc.MethodDescriptor.<com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest, com.ivanz.socialnetworkbackend.content.grpc.PostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.PostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PostServiceMethodDescriptorSupplier("UpdatePost"))
              .build();
        }
      }
    }
    return getUpdatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> getGetAllPostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllPosts",
      requestType = com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest.class,
      responseType = com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest,
      com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> getGetAllPostsMethod() {
    io.grpc.MethodDescriptor<com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest, com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> getGetAllPostsMethod;
    if ((getGetAllPostsMethod = PostServiceGrpc.getGetAllPostsMethod) == null) {
      synchronized (PostServiceGrpc.class) {
        if ((getGetAllPostsMethod = PostServiceGrpc.getGetAllPostsMethod) == null) {
          PostServiceGrpc.getGetAllPostsMethod = getGetAllPostsMethod =
              io.grpc.MethodDescriptor.<com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest, com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllPosts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PostServiceMethodDescriptorSupplier("GetAllPosts"))
              .build();
        }
      }
    }
    return getGetAllPostsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PostServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PostServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PostServiceStub>() {
        @java.lang.Override
        public PostServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PostServiceStub(channel, callOptions);
        }
      };
    return PostServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PostServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PostServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PostServiceBlockingStub>() {
        @java.lang.Override
        public PostServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PostServiceBlockingStub(channel, callOptions);
        }
      };
    return PostServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PostServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PostServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PostServiceFutureStub>() {
        @java.lang.Override
        public PostServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PostServiceFutureStub(channel, callOptions);
        }
      };
    return PostServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createPost(com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePostMethod(), responseObserver);
    }

    /**
     */
    default void getPostById(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPostByIdMethod(), responseObserver);
    }

    /**
     */
    default void deletePost(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeletePostMethod(), responseObserver);
    }

    /**
     */
    default void updatePost(com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePostMethod(), responseObserver);
    }

    /**
     */
    default void getAllPosts(com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllPostsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PostService.
   */
  public static abstract class PostServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PostServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PostService.
   */
  public static final class PostServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PostServiceStub> {
    private PostServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PostServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PostServiceStub(channel, callOptions);
    }

    /**
     */
    public void createPost(com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPostById(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPostByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deletePost(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeletePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePost(com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllPosts(com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest request,
        io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllPostsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PostService.
   */
  public static final class PostServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PostServiceBlockingStub> {
    private PostServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PostServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PostServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ivanz.socialnetworkbackend.content.grpc.PostResponse createPost(com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ivanz.socialnetworkbackend.content.grpc.PostResponse getPostById(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPostByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deletePost(com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeletePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ivanz.socialnetworkbackend.content.grpc.PostResponse updatePost(com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse getAllPosts(com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllPostsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PostService.
   */
  public static final class PostServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PostServiceFutureStub> {
    private PostServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PostServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PostServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> createPost(
        com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> getPostById(
        com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPostByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deletePost(
        com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeletePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ivanz.socialnetworkbackend.content.grpc.PostResponse> updatePost(
        com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse> getAllPosts(
        com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllPostsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POST = 0;
  private static final int METHODID_GET_POST_BY_ID = 1;
  private static final int METHODID_DELETE_POST = 2;
  private static final int METHODID_UPDATE_POST = 3;
  private static final int METHODID_GET_ALL_POSTS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_POST:
          serviceImpl.createPost((com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest) request,
              (io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse>) responseObserver);
          break;
        case METHODID_GET_POST_BY_ID:
          serviceImpl.getPostById((com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest) request,
              (io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse>) responseObserver);
          break;
        case METHODID_DELETE_POST:
          serviceImpl.deletePost((com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_POST:
          serviceImpl.updatePost((com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest) request,
              (io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PostResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_POSTS:
          serviceImpl.getAllPosts((com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest) request,
              (io.grpc.stub.StreamObserver<com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreatePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest,
              com.ivanz.socialnetworkbackend.content.grpc.PostResponse>(
                service, METHODID_CREATE_POST)))
        .addMethod(
          getGetPostByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
              com.ivanz.socialnetworkbackend.content.grpc.PostResponse>(
                service, METHODID_GET_POST_BY_ID)))
        .addMethod(
          getDeletePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_POST)))
        .addMethod(
          getUpdatePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest,
              com.ivanz.socialnetworkbackend.content.grpc.PostResponse>(
                service, METHODID_UPDATE_POST)))
        .addMethod(
          getGetAllPostsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest,
              com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse>(
                service, METHODID_GET_ALL_POSTS)))
        .build();
  }

  private static abstract class PostServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PostServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ivanz.socialnetworkbackend.content.grpc.PostProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PostService");
    }
  }

  private static final class PostServiceFileDescriptorSupplier
      extends PostServiceBaseDescriptorSupplier {
    PostServiceFileDescriptorSupplier() {}
  }

  private static final class PostServiceMethodDescriptorSupplier
      extends PostServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PostServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PostServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PostServiceFileDescriptorSupplier())
              .addMethod(getCreatePostMethod())
              .addMethod(getGetPostByIdMethod())
              .addMethod(getDeletePostMethod())
              .addMethod(getUpdatePostMethod())
              .addMethod(getGetAllPostsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
