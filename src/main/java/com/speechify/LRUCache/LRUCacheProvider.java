
Kancharla Vasavi <kancherlavasvi13@gmail.com>
9:47 AM (0 minutes ago)
to me

function createLRUCacheProvider(maxSize: number) {
  const cache = new Map();

  function get(key: string): any {
    if (!cache.has(key)) return undefined;

    const value = cache.get(key);
    cache.delete(key); // move to the end
    cache.set(key, value);
    return value;
  }

  function set(key: string, value: any): void {
    if (cache.has(key)) {
      cache.delete(key); // update order
    } else if (cache.size >= maxSize) {
      const oldestKey = cache.keys().next().value;
      cache.delete(oldestKey);
    }
    cache.set(key, value);
  }

  return { get, set };
}

