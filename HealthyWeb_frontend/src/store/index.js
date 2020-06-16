import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        selectedBuilding: {
            id: 0,
        },
        selectedRoom: {
            id: 0,
        },
        allFloors: [],
        currentOrder: {},
        orderBuldingSelected: [],
        currentDateOrder: "",
        floorEvent: {},

        //
        minimized: false
    },
    mutations: {
        buildingSelect(state, building) {
            state.selectedBuilding = building;
        },
        roomSelect(state, room) {
            state.selectedRoom = room;
        },
        fillFloors(state, floors) {
            state.allFloors = floors;
        },
        createFloor(state, floor) {
            state.allFloors.push(floor);
        },
        deleteFloor(state, floorId) {
            state.allFloors = state.allFloors.filter(item => {
                return item.id != floorId;
            });
        },
        orderUpdate(state, order) {
            state.currentOrder = order;
        },
        orderDateUpdate(state, orderDate) {
            state.currentDateOrder = orderDate;
        },
        orderBuildingSelect(state, orders) {
            state.orderBuldingSelected = orders;
        },
        // floor event add, delete, update
        floorEventMutations(state, value) {
            state.floorEvent = value;
        },
        changeMinimizedMutation(state, val) {
            state.minimized = !state.minimized;
        }
    },
    actions: {
        selectBuilding({ commit }, building) {
            commit('buildingSelect', building);
        },
        selectRoom({ commit }, room) {
            commit('roomSelect', room);
        },
        fillFloors({ commit }, floors) {
            commit('fillFloors', floors);
        },
        createFloor({ commit }, floor) {
            commit('createFloor', floor);
        },
        deleteFloor({ commit }, floorId) {
            commit('deleteFloor', floorId);
        },
        updateOrder({ commit }, order) {
            commit('orderUpdate', order);
        },
        updateOrderDate({ commit }, orderDate) {
            commit('orderDateUpdate', orderDate);
        },
        updateOrderBulding({ commit }, orders) {
            commit('orderBuildingSelect', orders);
        },
        // floor event add, delete, update
        floorActions({ commit }, value) {
            commit('floorEventMutations', value);
        },
        //

        changeMinimized({commit}) {
            commit('changeMinimizedMutation');
        }
    },
})
