/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.admin.v2;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.bigtable.admin.v2.ClusterName;
import com.google.bigtable.admin.v2.DeleteClusterRequest;
import com.google.bigtable.admin.v2.DeleteInstanceRequest;
import com.google.bigtable.admin.v2.GetClusterRequest;
import com.google.bigtable.admin.v2.GetInstanceRequest;
import com.google.bigtable.admin.v2.InstanceName;
import com.google.bigtable.admin.v2.ListClustersRequest;
import com.google.bigtable.admin.v2.ListClustersResponse;
import com.google.bigtable.admin.v2.ListInstancesRequest;
import com.google.bigtable.admin.v2.ListInstancesResponse;
import com.google.bigtable.admin.v2.LocationName;
import com.google.bigtable.admin.v2.ProjectName;
import com.google.cloud.bigtable.admin.v2.models.Cluster;
import com.google.cloud.bigtable.admin.v2.models.CreateClusterRequest;
import com.google.cloud.bigtable.admin.v2.models.CreateInstanceRequest;
import com.google.cloud.bigtable.admin.v2.models.Instance;
import com.google.cloud.bigtable.admin.v2.models.PartialListClustersException;
import com.google.cloud.bigtable.admin.v2.models.PartialListInstancesException;
import com.google.cloud.bigtable.admin.v2.models.UpdateInstanceRequest;
import com.google.cloud.bigtable.admin.v2.stub.BigtableInstanceAdminStub;
import com.google.common.base.Verify;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;

/**
 * Client for creating, configuring and delete Cloud Bigtable instances (including AppProfiles and
 * Clusters).
 *
 * <p>See the individual methods for example code.
 *
 * <pre>{@code
 * try(BigtableInstanceAdminClient client =  BigtableInstanceAdminClient.create(ProjectName.of("my-project"))) {
 *   CreateInstanceRequest request = CreateInstanceRequest.of("my-instance")
 *     .addCluster("my-cluster", "us-east1-c", 3, StorageType.SSD);
 *
 *   Instance instance = client.createInstance(request);
 * }
 * }</pre>
 *
 * <p>Note: close() needs to be called on the client object to clean up resources such as threads.
 * In the example above, try-with-resources is used, which automatically calls close().
 *
 * <p>This class can be customized by passing in a custom instance of BigtableInstanceAdminSettings
 * to create(). For example:
 *
 * <p>To customize credentials:
 *
 * <pre>{@code
 * BigtableInstanceAdminSettings settings = BigtableInstanceAdminSettings.newBuilder()
 *   .setProjectName(ProjectName.of("my-project"))
 *   .setCredentialsProvider(FixedCredentialsProvider.create(myCredentials))
 *   .build();
 *
 * BigtableInstanceAdminClient client = BigtableInstanceAdminClient.create(settings);
 * }</pre>
 *
 * To customize the endpoint:
 *
 * <pre>{@code
 * BigtableInstanceAdminSettings settings = BigtableInstanceAdminSettings.newBuilder()
 *   .setProjectName(ProjectName.of("my-project"))
 *   .setEndpoint(myEndpoint)
 *   .build();
 *
 * BigtableInstanceAdminClient client = BigtableInstanceAdminClient.create(settings);
 * }</pre>
 */
public final class BigtableInstanceAdminClient implements AutoCloseable {
  private final ProjectName projectName;
  private final BigtableInstanceAdminStub stub;

  /** Constructs an instance of BigtableInstanceAdminClient with the given ProjectName. */
  public static BigtableInstanceAdminClient create(@Nonnull ProjectName projectName)
      throws IOException {
    return create(BigtableInstanceAdminSettings.newBuilder().setProjectName(projectName).build());
  }

  /** Constructs an instance of BigtableInstanceAdminClient with the given settings. */
  public static BigtableInstanceAdminClient create(@Nonnull BigtableInstanceAdminSettings settings)
      throws IOException {
    return create(settings.getProjectName(), settings.getStubSettings().createStub());
  }

  /** Constructs an instance of BigtableInstanceAdminClient with the given ProjectName and stub. */
  public static BigtableInstanceAdminClient create(@Nonnull ProjectName projectName,
      @Nonnull BigtableInstanceAdminStub stub) {
    return new BigtableInstanceAdminClient(projectName, stub);
  }


  private BigtableInstanceAdminClient(
      @Nonnull ProjectName projectName, @Nonnull BigtableInstanceAdminStub stub) {
    this.projectName = projectName;
    this.stub = stub;
  }

  /** Gets the ProjectName this client is associated with. */
  @SuppressWarnings("WeakerAccess")
  public ProjectName getProjectName() {
    return projectName;
  }

  /** Closes the client and frees all resources associated with it (like thread pools) */
  @Override
  public void close() {
    stub.close();
  }

  /**
   * Creates a new instance and returns its representation.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Instance instance = client.createInstance(
   *   CreateInstanceRequest.of("my-instance")
   *     .addCluster("my-cluster", "us-east1-c", 3, StorageType.SSD)
   * );
   * }</pre>
   *
   * @see CreateInstanceRequest for details.
   */
  @SuppressWarnings("WeakerAccess")
  public Instance createInstance(CreateInstanceRequest request) {
    return awaitFuture(createInstanceAsync(request));
  }

  /**
   * Asynchronously creates a new instance and returns its representation wrapped in a future.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Instance> instanceFuture = client.createInstanceAsync(
   *   CreateInstanceRequest.of("my-instance")
   *     .addCluster("my-cluster", "us-east1-c", 3, StorageType.SSD)
   * );
   *
   * Instance instance = instanceFuture.get();
   * }</pre>
   *
   * @see CreateInstanceRequest for details.
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Instance> createInstanceAsync(CreateInstanceRequest request) {
    return ApiFutures.transform(
        stub.createInstanceOperationCallable().futureCall(request.toProto(projectName)),
        new ApiFunction<com.google.bigtable.admin.v2.Instance, Instance>() {
          @Override
          public Instance apply(com.google.bigtable.admin.v2.Instance proto) {
            return Instance.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor());
  }

  /**
   * Updates a new instance and returns its representation.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Instance instance = client.updateInstance(
   *   UpdateInstanceRequest.of("my-instance")
   *     .setProductionType()
   * );
   * }</pre>
   *
   * @see UpdateInstanceRequest for details.
   */
  @SuppressWarnings("WeakerAccess")
  public Instance updateInstance(UpdateInstanceRequest request) {
    return awaitFuture(updateInstanceAsync(request));
  }

  /**
   * Asynchronously updates a new instance and returns its representation wrapped in a future.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Instance> instanceFuture = client.updateInstanceAsync(
   *   UpdateInstanceRequest.of("my-instance")
   *     .setProductionType()
   * );
   *
   * Instance instance = instanceFuture.get();
   * }</pre>
   *
   * @see UpdateInstanceRequest for details.
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Instance> updateInstanceAsync(UpdateInstanceRequest request) {
    return ApiFutures.transform(
        stub.partialUpdateInstanceOperationCallable().futureCall(request.toProto(projectName)),
        new ApiFunction<com.google.bigtable.admin.v2.Instance, Instance>() {
          @Override
          public Instance apply(com.google.bigtable.admin.v2.Instance proto) {
            return Instance.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor());
  }

  /**
   * Get the instance representation by ID.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Instance instance = client.getInstance("my-instance");
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public Instance getInstance(String id) {
    return awaitFuture(getInstanceAsync(id));
  }

  /**
   * Asynchronously gets the instance representation by ID wrapped in a future.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Instance> instanceFuture = client.getInstanceAsync("my-instance");
   * Instance instance = instanceFuture.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Instance> getInstanceAsync(String instanceId) {
    InstanceName name = InstanceName.of(projectName.getProject(), instanceId);

    GetInstanceRequest request = GetInstanceRequest.newBuilder()
        .setName(name.toString())
        .build();

    return ApiFutures.transform(
        stub.getInstanceCallable().futureCall(request),
        new ApiFunction<com.google.bigtable.admin.v2.Instance, Instance>() {
          @Override
          public Instance apply(com.google.bigtable.admin.v2.Instance proto) {
            return Instance.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor());
  }

  /**
   * Lists all of the instances in the current project.
   *
   * <p>This method will throw a {@link PartialListInstancesException} when any zone is
   * unavailable. If partial listing are ok, the exception can be caught and inspected.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try {
   *   List<Instance> instances = client.listInstances();
   * } catch (PartialListInstancesException e) {
   *   System.out.println("The following zones are unavailable: " + e.getUnavailableZones());
   *   System.out.println("But the following instances are reachable: " + e.getInstances());
   * }
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public List<Instance> listInstances() {
    return awaitFuture(listInstancesAsync());
  }

  /**
   * Asynchronously lists all of the instances in the current project.
   *
   * <p>This method will throw a {@link PartialListInstancesException} when any zone is
   * unavailable.
   * If partial listing are ok, the exception can be caught and inspected.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Instance> instancesFuture = client.listInstancesAsync();
   *
   * ApiFutures.addCallback(instancesFuture, new ApiFutureCallback<List<Instance>>() {
   *   public void onFailure(Throwable t) {
   *     if (t instanceof PartialListInstancesException) {
   *       PartialListInstancesException partialError = (PartialListInstancesException)t;
   *       System.out.println("The following zones are unavailable: " + partialError.getUnavailableZones());
   *       System.out.println("But the following instances are reachable: " + partialError.getInstances());
   *     } else {
   *       t.printStackTrace();
   *     }
   *   }
   *
   *   public void onSuccess(List<Instance> result) {
   *     System.out.println("Found a complete set of instances: " + result);
   *   }
   * }, MoreExecutors.directExecutor());
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<List<Instance>> listInstancesAsync() {
    ListInstancesRequest request = ListInstancesRequest.newBuilder()
        .setParent(projectName.toString())
        .build();

    ApiFuture<ListInstancesResponse> responseFuture = stub.listInstancesCallable()
        .futureCall(request);

    return ApiFutures
        .transform(responseFuture, new ApiFunction<ListInstancesResponse, List<Instance>>() {
          @Override
          public List<Instance> apply(ListInstancesResponse proto) {
            // NOTE: pagination is intentionally ignored. The server does not implement it and never
            // will.
            Verify.verify(proto.getNextPageToken().isEmpty(),
                "Server returned an unexpected paginated response");

            ImmutableList.Builder<Instance> instances = ImmutableList.builder();

            for (com.google.bigtable.admin.v2.Instance protoInstance : proto.getInstancesList()) {
              instances.add(Instance.fromProto(protoInstance));
            }

            ImmutableList.Builder<String> failedZones = ImmutableList.builder();
            for (String locationStr : proto.getFailedLocationsList()) {
              LocationName fullLocation = Objects.requireNonNull(LocationName.parse(locationStr));
              failedZones.add(fullLocation.getLocation());
            }

            if (!failedZones.build().isEmpty()) {
              throw new PartialListInstancesException(failedZones.build(), instances.build());
            }

            return instances.build();
          }
        }, MoreExecutors.directExecutor());
  }

  /**
   * Deletes the specified instance.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * client.deleteInstance("my-instance");
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public void deleteInstance(String instanceId) {
    awaitFuture(deleteInstanceAsync(instanceId));
  }

  /**
   * Asynchronously deletes the specified instance.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Void> deleteFuture = client.deleteInstance("my-instance");
   * deleteFuture.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Void> deleteInstanceAsync(String instanceId) {
    InstanceName instanceName = InstanceName.of(projectName.getProject(), instanceId);

    DeleteInstanceRequest request = DeleteInstanceRequest.newBuilder()
        .setName(instanceName.toString())
        .build();

    return ApiFutures.transform(stub.deleteInstanceCallable().futureCall(request),
        new ApiFunction<Empty, Void>() {
          @Override
          public Void apply(Empty input) {
            return null;
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Creates a new cluster in the specified instance.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Cluster cluster = client.createCluster(
   *   CreateClusterRequest.of("my-instance", "my-new-cluster")
   *     .setZone("us-east1-c")
   *     .setServeNodes(3)
   *     .setStorageType(StorageType.SSD)
   * );
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public Cluster createCluster(CreateClusterRequest request) {
    return awaitFuture(createClusterAsync(request));
  }

  /**
   * Asynchronously creates a new cluster in the specified instance.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Cluster> clusterFuture = client.createClusterAsync(
   *   CreateClusterRequest.of("my-instance", "my-new-cluster")
   *     .setZone("us-east1-c")
   *     .setServeNodes(3)
   *     .setStorageType(StorageType.SSD)
   * );
   *
   * Cluster cluster = clusterFuture.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Cluster> createClusterAsync(CreateClusterRequest request) {
    return ApiFutures.transform(
        stub.createClusterOperationCallable().futureCall(request.toProto(projectName)),
        new ApiFunction<com.google.bigtable.admin.v2.Cluster, Cluster>() {
          @Override
          public Cluster apply(com.google.bigtable.admin.v2.Cluster proto) {
            return Cluster.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Get the cluster representation by ID.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Cluster cluster = client.getCluster("my-instance", "my-cluster");
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public Cluster getCluster(String instanceId, String clusterId) {
    return awaitFuture(getClusterAsync(instanceId, clusterId));
  }

  /**
   * Asynchronously gets the cluster representation by ID.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Cluster> clusterFuture = client.getClusterAsync("my-instance", "my-cluster");
   * Cluster cluster = clusterFuture.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Cluster> getClusterAsync(String instanceId, String clusterId) {
    ClusterName name = ClusterName.of(projectName.getProject(), instanceId, clusterId);

    GetClusterRequest request = GetClusterRequest.newBuilder()
        .setName(name.toString())
        .build();

    return ApiFutures.transform(
        stub.getClusterCallable().futureCall(request),
        new ApiFunction<com.google.bigtable.admin.v2.Cluster, Cluster>() {
          @Override
          public Cluster apply(com.google.bigtable.admin.v2.Cluster proto) {
            return Cluster.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Lists all clusters in the specified instance.
   *
   * <p>This method will throw a {@link PartialListClustersException} when any zone is
   * unavailable. If partial listing are ok, the exception can be caught and inspected.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try {
   *   List<Cluster> clusters = cluster.listClusters("my-instance");
   * } catch (PartialListClustersException e) {
   *   System.out.println("The following zones are unavailable: " + e.getUnavailableZones());
   *   System.out.println("But the following clusters are reachable: " + e.getClusters())
   * }
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public List<Cluster> listClusters(String instanceId) {
    return awaitFuture(listClustersAsync(instanceId));
  }

  /**
   * Asynchronously lists all clusters in the specified instance.
   *
   * <p>This method will throw a {@link PartialListClustersException} when any zone is
   * unavailable. If partial listing are ok, the exception can be caught and inspected.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Cluster> clustersFuture = client.listClustersAsync("my-instance");
   *
   * ApiFutures.addCallback(clustersFuture, new ApiFutureCallback<List<Cluster>>() {
   *   public void onFailure(Throwable t) {
   *     if (t instanceof PartialListClustersException) {
   *       PartialListClustersException partialError = (PartialListClustersException)t;
   *       System.out.println("The following zones are unavailable: " + partialError.getUnavailableZones());
   *       System.out.println("But the following clusters are reachable: " + partialError.getClusters());
   *     } else {
   *       t.printStackTrace();
   *     }
   *   }
   *
   *   public void onSuccess(List<Cluster> result) {
   *     System.out.println("Found a complete set of instances: " + result);
   *   }
   * }, MoreExecutors.directExecutor());
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<List<Cluster>> listClustersAsync(String instanceId) {
    InstanceName name = InstanceName.of(projectName.getProject(), instanceId);
    ListClustersRequest request = ListClustersRequest.newBuilder()
        .setParent(name.toString())
        .build();

    return ApiFutures.transform(
        stub.listClustersCallable().futureCall(request),
        new ApiFunction<ListClustersResponse, List<Cluster>>() {
          @Override
          public List<Cluster> apply(ListClustersResponse proto) {
            // NOTE: serverside pagination is not and will not be implemented, so remaining pages
            // are not fetched. However, if that assumption turns out to be wrong, fail fast to
            // avoid returning partial data.
            Verify.verify(proto.getNextPageToken().isEmpty(),
                "Server returned an unexpected paginated response");

            ImmutableList.Builder<Cluster> clusters = ImmutableList.builder();
            for (com.google.bigtable.admin.v2.Cluster cluster : proto.getClustersList()) {
              clusters.add(Cluster.fromProto(cluster));
            }

            ImmutableList.Builder<String> failedZones = ImmutableList.builder();
            for (String locationStr : proto.getFailedLocationsList()) {
              LocationName fullLocation = Objects.requireNonNull(LocationName.parse(locationStr));
              failedZones.add(fullLocation.getLocation());
            }

            if (!failedZones.build().isEmpty()) {
              throw new PartialListClustersException(failedZones.build(), clusters.build());
            }

            return clusters.build();
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Resizes the cluster's node count. Please note that only clusters that belong to a PRODUCTION
   * instance can be resized.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * Cluster cluster = cluster.resizeCluster("my-instance", "my-cluster", 30);
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public Cluster resizeCluster(String instanceId, String clusterId, int numServeNodes) {
    return awaitFuture(resizeClusterAsync(instanceId, clusterId, numServeNodes));
  }

  /**
   * Asynchronously resizes the cluster's node count. Please note that only clusters that belong to
   * a PRODUCTION instance can be resized.
   *
   * <pre>{@code
   * ApiFuture<Cluster> clusterFuture = cluster.resizeCluster("my-instance", "my-cluster", 30);
   * Cluster cluster = clusterFuture.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public  ApiFuture<Cluster> resizeClusterAsync(String instanceId, String clusterId,
      int numServeNodes) {

    ClusterName name = ClusterName.of(projectName.getProject(), instanceId, clusterId);

    com.google.bigtable.admin.v2.Cluster request = com.google.bigtable.admin.v2.Cluster.newBuilder()
        .setName(name.toString())
        .setServeNodes(numServeNodes)
        .build();

    return ApiFutures.transform(
        stub.updateClusterOperationCallable().futureCall(request),
        new ApiFunction<com.google.bigtable.admin.v2.Cluster, Cluster>() {
          @Override
          public Cluster apply(com.google.bigtable.admin.v2.Cluster proto) {
            return Cluster.fromProto(proto);
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Deletes the specified cluster. Please note that an instance must have at least 1 cluster. To
   * remove the last cluster, please use {@link BigtableInstanceAdminClient#deleteInstance(String)}.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * client.deleteCluster("my-instance", "my-cluster");
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public void deleteCluster(String instanceId, String clusterId) {
    awaitFuture(deleteClusterAsync(instanceId, clusterId));
  }

  /**
   * Asynchronously deletes the specified cluster. Please note that an instance must have at least 1
   * cluster. To remove the last cluster, please use {@link BigtableInstanceAdminClient#deleteInstanceAsync(String)}.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * ApiFuture<Void> future = client.deleteClusterAsync("my-instance", "my-cluster");
   * future.get();
   * }</pre>
   */
  @SuppressWarnings("WeakerAccess")
  public ApiFuture<Void> deleteClusterAsync(String instanceId, String clusterId) {
    ClusterName name = ClusterName.of(projectName.getProject(), instanceId, clusterId);

    DeleteClusterRequest request = DeleteClusterRequest.newBuilder()
        .setName(name.toString())
        .build();

    return ApiFutures.transform(
        stub.deleteClusterCallable().futureCall(request),
        new ApiFunction<Empty, Void>() {
          @Override
          public Void apply(Empty input) {
            return null;
          }
        },
        MoreExecutors.directExecutor()
    );
  }

  /**
   * Awaits the result of a future, taking care to propagate errors while maintaining the call site
   * in a suppressed exception. This allows semantic errors to be caught across threads, while
   * preserving the call site in the error. The caller's stacktrace will be made available as a
   * suppressed exception.
   */
  // TODO(igorbernstein2): try to move this into gax
  private <T> T awaitFuture(ApiFuture<T> future) {
    RuntimeException error;
    try {
      return Futures.getUnchecked(future);
    } catch (UncheckedExecutionException e) {
      if (e.getCause() instanceof RuntimeException) {
        error = (RuntimeException) e.getCause();
      } else {
        error = e;
      }
    } catch (RuntimeException e) {
      error = e;
    }
    // Add the caller's stack as a suppressed exception
    error.addSuppressed(new RuntimeException("Encountered error while awaiting future"));
    throw error;
  }
}