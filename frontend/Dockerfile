# Using the official Node.js image as the base image
FROM node:20

# Setting the working directory in the container
WORKDIR /app

# Copying package.json and package-lock.json into the container
COPY package*.json ./

# Installing dependencies
RUN npm install

# Copying the rest of the application files into the container
COPY . .

# Exposing port 3000 for the React application
EXPOSE 3000

# Specifying the command to run when the container starts
CMD ["npm", "start"]
