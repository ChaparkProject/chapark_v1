import axios from 'axios';

const axiosInstance = axios.create({});

export const Get = async (url, config) => {
  const response = await axiosInstance.get(url, config);
  return response;
};

export const Post = async (url, data, config) => {
  const response = await axiosInstance.post(url, data, config);
  return response;
};

export const Put = async (url, data, config) => {
  const response = await axiosInstance.put(url, data, config);
  return response;
};

export const Patch = async (url, data, config) => {
  const response = await axiosInstance.patch(url, data, config);
  return response;
};

export const Delete = async (url, config) => {
  const response = await axiosInstance.delete(url, config);
  return response;
};
export const objectToQueryString = async (obj, prefix) => {
  const queryString = Object.keys(obj).map(key => {
    const value = obj[key];
    const encodedKey = prefix ? `${prefix}[${encodeURIComponent(key)}]` : encodeURIComponent(key);

    if (value !== null && typeof value === 'object') {
      return objectToQueryString(value, encodedKey);
    } else {
      return `${encodedKey}=${encodeURIComponent(value)}`;
    }
  });

  return queryString.join('&');
};