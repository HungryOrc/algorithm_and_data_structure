


            for (DirectedGraphNode neighbor : curNode.neighbors) {
                
                inDegrees.put(neighbor, inDegrees.get(neighbor) - 1);
                
                if (inDegrees.get(neighbor) == 0) {
                    nodeQueue.offer(neighbor);
                    topoOrder.add(neighbor);
                }
            }
        }
        return topoOrder;
    }
    
}
